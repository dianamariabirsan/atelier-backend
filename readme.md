#Online store
##Architecture
###Entities
BaseEntity(id)
Client(name, email, phoneNumber)  
Product(type, price)  
Status:Enum (pending, approved, on shipping)  
Order(Client, List<Product> products, status)  
