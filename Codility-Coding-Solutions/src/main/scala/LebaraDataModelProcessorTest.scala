/**
  * Created by govind.bhone on 2/2/2017.
  */

case class Product(productName:String,category:String,price:Double)
case class ProductDetails(product:Product,quantity:Int)
case class Order(productDetailsList:List[ProductDetails])
case class User(userName:String,orderDetailsList:List[Order])

trait AbstractDataModelProcessor {
  // 1 - Given an order, produce a list of product names.
  def productNamesFromOrder(order: Order): List[String]

  // 2 - Given a user, produce a list of products from all of their orders.
  def productsFromUser(user: User): List[Product]

  // 3 - Given an order, calculate the total price.
  def orderTotal(order: Order): Double

  // 4 - Given a list of products and a category name, produce a list of products in that category.
  // Make sure the solution handles a possibly missing category on a product.
  def productsInCategory(products: List[Product], category: String): List[Product]

  // 5 - Given list of products, produce a map of category to a list of products in that category.
  // Make sure the solution handles a possibly missing category on a product.
  def productsByCategory(products: List[Product]): Map[String, List[Product]]
}


class DataModelProcessor extends AbstractDataModelProcessor{
  // 1 - Given an order, produce a list of product names.
  override def productNamesFromOrder(order: Order): List[String] = order.productDetailsList.map(_.product.productName)

  // 2 - Given a user, produce a list of products from all of their orders.
  override def productsFromUser(user: User): List[Product] = user.orderDetailsList.flatMap(_.productDetailsList).map(_.product)

  // 3 - Given an order, calculate the total price.
  override def orderTotal(order: Order): Double = order.productDetailsList.foldLeft(0d){case (acc,ProductDetails(product,quantity))=>acc + product.price*quantity}


  // 4 - Given a list of products and a category name, produce a list of products in that category.
  override def productsInCategory(products: List[Product], category: String): List[Product] = products.filter(_.category==category)

  // 5 - Given list of products, produce a map of category to a list of products in that category.
  override def productsByCategory(products: List[Product]): Map[String, List[Product]] = products.groupBy(_.category)
}

object DataModelProcessor{
  def apply()=new DataModelProcessor
}

object LebaraDataModelProcessorTest extends App {
  val products1 = Product("BlackBerry Pant", "Clothing", 34.5)
  val products2 = Product("BlackBerry Shirt", "Clothing", 14.5)
  val products3 = Product("Cricket Bat", "Sports", 9.5)
  val products4 = Product("Cricket Ball", "Sports", 5)
  //product with missing category
  val products5 = Product("Ceiling Fan", "", 20)

  val productDetails1 = ProductDetails(products1, 2)
  val productDetails2 = ProductDetails(products2, 3)
  val productDetails3 = ProductDetails(products3, 2)
  val productDetails4 = ProductDetails(products4, 5)

  val order1 = Order(List(productDetails1))
  val order2 = Order(List(productDetails1, productDetails2))
  val order3 = Order(List(productDetails1, productDetails2, productDetails3))
  val order4 = Order(List(productDetails1, productDetails2, productDetails3, productDetails4))

  val user1 = User("Govind", List(order1))
  val user2 = User("Roy", List(order1, order2))
  val user3 = User("Ria", List(order1, order2, order3))
  val user4 = User("Mark", List(order1, order2, order3, order4))

  val category = "Sports"

  val dataModelProcessor = DataModelProcessor()
  println("All Product Names for Order : " + dataModelProcessor.productNamesFromOrder(order4))
  println("All Products for User " + dataModelProcessor.productsFromUser(user4))
  println("Order Total Amount : $" + dataModelProcessor.orderTotal(order4))
  println(s"Products In Category ${category} : ${dataModelProcessor.productsInCategory(List(products1, products2, products3, products4, products5), category)} ")
  println(s"Products In Category ${"Accessaries"} : ${dataModelProcessor.productsInCategory(List(products1, products2, products3, products4, products5), "Accessaries")} ")
  println("Mapping products with categories : " + dataModelProcessor.productsByCategory(List(products1, products2, products3, products4, products5)))
}
