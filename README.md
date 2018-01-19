# Hibernate Framework

> Hibernate Introduction and basic opertaion

>Hibernate concept and API usages
 
>Hibernate configuration, 1to many and many to many

> Hibernate query

# Chapter 1 Introduction  

## Review of webcontent

1. Java EE three Layers

* Web － struts2 框架
	
* Service － Spring 框架

* Dao － Hibernate 框架

A comparison between JDBC and Hibernate:
 
 ![comparison between JDBC and Hibernate](https://github.com/darkcar/hibernate-framework/blob/master/note/hibernate-jdbc.jpeg?raw=true)

2. MVC 思想: 是一种公用的思想

* M： 模型

* V：视图

* C：控制器

## Hibernate introduction

1. What is Hibernate？

框架： 写一个程序，使用框架之后，能帮我们实现一部分功能。使用框架好处，少写一部分代码。

Hibernate（冬眠） 框架：应用在javaEE三层中的dao层的框架。

在dao层里面做对数据库crud操作，使用hibernate实现crud操作，hibernate底层代码就是jdbc，hibernate对jdbc进行了封装。

好处，1.不需要写复杂jdbc的代码了。2. 不需要写sql语句。3. Hibernate开源轻量级框架。4. Hibernate版本5.x。

2. What is ORM?

在web中，javabean可以封装数据，在框架阶段，叫做实体类。

Object－Relational－Mapping：对象关系映射， 让实体类和数据表进行一一对应关系，让实体类属性和表里面字段对应。不需要直接操作数据库表，而操作表对应实体类就行。

Find the detailed comparison from the picture under /note.

## Hibernate quick start

1. Create Hibernate Environment

* import jar files: folder (JPA + Requires) + log4j.jar + mysql-connector-java.jar + slf4j-api.jar + slf4j-log4j12.jar

* Create Entity Class (JavaBean)

```java
public class User {
	/* one unique value */
	private int uid;
	private String username;
	private String password;
	private String address;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
```

* Create database table (Hibernate has tool to create the table automatically without manually doing that).

* 配置实体类和数据库表一一对应关系（映射关系）

1. Create xml configuration file，实体类所在的包里进行创建，实体类名称.hbm.xml

2. Include hibernate xml dtd. 

```xml
<!DOCTYPE hibernate-mapping PUBLIC 
    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
```

3. Configure mapping information.

```xml
<hibernate-mapping>
	<!-- 
		1. Class Map to Table
		name: Full path to Entity class
		table: Table name in DB
	 -->
	<class name="com.liyiandxuegang.entity.User" table="t_user">
		<!-- 2. Map Entity id to Table id -->
		<!-- 
			id has two properties:
			name: Entity id name
			column: Table column name	
		 -->
		<id name="uid" column="uid">
			<!-- 
				Increase strategy id by?
				native: primary key, auto_increment 
			 -->
			<generator class="native"></generator>
		</id>
		<!-- 3. Map other fields in Entity to Table -->
		<property name="username" column="username" />
		<property name="password" column="password" />
		<property name="address" column="address" />
	</class>
</hibernate-mapping>
```

4. Create hibernate core configuration file: name and location is fixed. (under src)

* xml header

```xml
<!DOCTYPE hibernate-configuration PUBLIC
	"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
```

* hibernate操作过程中，自动加载的文件只有核心配置文件，其他配置文件不会加载。

```xml
<hibernate-configuration>
	<session-factory>
		<!-- Part 1: DB Information -->
		<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
		<property name="hibernate.connection.url">jdbc:mysql://localhost:8889/test</property>
		<property name="hibernate.connection.username">root</property>
		<property name="hibernate.connection.password">root123</property>
		
		<!-- Part 2: Hibernate Information (Optional) -->
		<property name="hibernate.show_sql">true</property>
		<property name="hibernate.format_sql">true</property>
		<!-- 
			Hibernate Create table, after we configure the settings
			update: If no table, create table. 
		 -->
		<property name="hibernate.hbm2ddl.auto">update</property>
		<!-- 
			db Dialect
		 -->
		<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
		
		<!-- Part 3: Including mapping xml -->
		<mapping resource="com/liyiandxuegang/entity/User.hbm.xml" />
	</session-factory>
</hibernate-configuration>
```

5. Test: Load core setting xml file -> Create SessionFactory -> Create session object -> Open Transaction -> Logic -> Commit -> Close Resources

```java
@Test
	public void test() {
		// 1. Load core xml file, since name and loaction is fixed, so no params needed. 
		Configuration cfg = new Configuration();
		cfg.configure();
		
		// 2. Create SessionFactory, according to mapping relation, create table in DB.
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		// 3. Create session object, like connections in JDBC
		Session session = sessionFactory.openSession();
		
		// 4. Create transaction
		Transaction transaction = session.beginTransaction();
		
		// 5. CRUD
		// Insert
		User user = new User();
		user.setUsername("Frank Wang");
		user.setPassword("123456");
		user.setAddress("Canada");
		// Call session save
		session.save(user);
		
		// 6. Commit transactions
		transaction.commit();
		
		// 7. Close resources
		session.close();
		sessionFactory.close();
	}
```

## Hibernate configuration files details

## Hibernate Mapping xml (Entity.hbm.xml)

* No requires for filename and location

* Class tag: name property: Entity full path

* Name property values in id tag and property tags should be same as Entity properties.

## Hibernate Core xml (hibernate.cfg.xml)

* All subtags should be under <session-factory>

* Part 1 required, Part 2 optional, Part 3 required.

* File name and location should be fixed. 

## Hibernate API 

### Configuration

```java
Configuration cfg = new Configuration();
cfg.configure();
```

[Load configuration file] Program will go to src folder, and find "hibernate.cfg.xml" file. Then, load configuration file into Object. 

### SessionFactory (*)

```java
// What will this do?
// 1. Connect to db, and create the table, if it is not disabled.  
// 2. It needs lots of resources.
// 3. One project, have one sessionFactory object.
cfg.buildSessionFactory(); 
```

How to create only one sessionFactory Object
```java
// Create util class, and use static code block.
public class HibernateUtils {
	private static final Configuration cfg;
	private static final SessionFactory sessionFactory;
	// Static, only run once.
	static {
		cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	// return session factory 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

```

### Session

1. session类似于jdbc中的connection

2. 调用session里面的方法实现crud的操作。
	save， update， delete， get

3. session对象是单线程对象， session对象不能公用，只能自己使用。 

### Transaction  

1. Transaction Object: beginTransaction(), 

2. Methods: commit(), rollback();

3. Concept:

原子性，一致性，隔离性，持久性；

## What to do if there is no tips for xml editing?

* Step 1. Open preferences, and search "xml catalog"

* Step 2. Click Add, fill the following information.

1. Location: Find dtd or schema file from local system.

2. Key Type: URI;

3. Key: Copy the url http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd; 

4. Restart Eclipse.

# Chapter 2 Hibernate Advanced

## 实体类编写规范

1. 属性私有化

2. 方法set和get要使用public修饰

3. 实体类有一个属性必须有唯一值。

4. 实体类属性，建议不适应基本数据类型，而使用基本数据对应的包装类
	
	* 八个数据基本类型：int-> Integer; char -> Character; double -> Double
	
	* why? For example: int score; 
	
	- If student got 0, int score = 0; but if the student missed the exam, int score = 0 can not represent the student didn't show up. 
	
	- But if Integer score = 0, means student got 0; If no show, then Integer score = null; 
	
## Hibernate 主键生成策略

```xml
<generator class="native"></generator>
```

### native

根据底层数据库对自动生成表示符的能力来选择identity，sequence，hilo三种生成器的一种。

### uuid

Hibernate采用128为的UUID算法来生成标识符。该算法能够在网络环境中生成唯一的字符串标识符，其UUID被编码为一个长度为32位的十六进制字符串。
这种策略不流行，因为字符串类型的主键比整数类型的主键占用更多的数据库空间。适用于代理主键。

```java
private String uuid;
```
 
 and in xml settings, we need to change id generator by uuid
 
 ```xml
 <generator class="uuid"></generator>
 ```
 
### Operation for Entity

1. Call method save of session

```java
session.save(user);
```

2. Query by Id: call method of get in session

```java
	@Test
	public void test1() {
		// Call sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		//Open transactions
		Transaction transaction = session.beginTransaction();
		// Query by id
		User user = session.get(User.class, 1);
		System.out.println(user);
		// Commit 
		transaction.commit();
		// Close
		session.close();
		sessionFactory.close();
	}
```

3. Update: First query and then update

```java
User user = session.get(User.class, 1);
user.setPassowrd("123");
session.update(user); // session.save(user); 
```

4. Delete: 

* Query get the Entity instance, and delete (recommend method)

```java
User user = session.get(User.class, 1);
session.delete(user);
```

* Create Entity instance, and delete (Not recommended)

```java
User user = new User();
user.setUid(1);
session.delete(user);
```

### Status of Entity

1. 瞬时态: 对象中没有id值，对象和session没有关联。主要用来save操作。

```java
User user = new User();
user.setUsername("Frank");
user.setPassword("*sdfsfasww");
user.setAddress("Earch");
session.save(user);
```

2. 持久态：对象中有id值。并且和session有关联。

```java
User user = session.get(User.class, 1);
```

3. 托管态: 对象有id值，但是对象跟session无关系。 （使用的不多）

```java
User user=  new User();
user.setUid(3);
session.save(user);
```
 
 ## Other methods
 
 ### saveOrUpdate() 
 
 Add new record
 
 ```java
 User user = new User();
 user.setUsername("jack");
 user.setPassword("529");
 user.setAddress("Korean");
 
 session.saveOrUpdate(user);
 ```
 
 Update record
 
 ```java
 User user = new User();
 user.setUid(6);
 user.setUsername("Rose");
 user.setPassword("12312");
 user.setAddress("Harbour Landing");
 
 session.saveOrUpdate(user);
 ```
 
 update record
 
 ```java
 User user = session.get(User.class, 7);
 user.setUsername("LiLei");
 
 session.saveOrUpdate(user);
 ```
 
 ## Hibernate First level cache
 
 ### 什么是缓存？
 
 数据存到数据库，而数据库本身是一个文件系统，Java中要使用流的方式来操作效率不是很高。
 
 - 我们可以把数据存到内存中，不需要流方式读取，直接读取内存中数据。
 
 - 把数据放到内存中，提高读取效率
 
 ### Hibernate Cache
 
 1. Hibernate框架中提供了很多优化方式，hibernate的缓存就是一个优化方式。
 
 2. 缓存特点：
 
 * Hibernate first-level cache: 默认是开启状态；使用范围是session范围；存储的数据必须为持久态数据；
 
 * Hibernate second-level cache: 二级缓存已经被redis替代。需要配置来打开，使用范围是sessionFactory范围。
 
 ### Example (Verify first level cache)
 
 ```java
 User user1 = session.get(User.class, 6);
 System.out.println(user1);
 
 User user2 = session.get(User.class, 6);
 System.out.println(user2);
 ```
 
 The second time, hibernate won't query db, and the console prints the message below:
 
 ```txt
 Hibernate: 
    select
        user0_.uid as uid1_0_0_,
        user0_.username as username2_0_0_,
        user0_.password as password3_0_0_,
        user0_.address as address4_0_0_ 
    from
        t_user user0_ 
    where
        user0_.uid=?
User [uid=6, username=Liyi, password=123456, address=Canada]
User [uid=6, username=Liyi, password=123456, address=Canada]s
 ```
 
 It only do the query once.  
 
 See below how does it work? 
 
  ![Including images for hibernate first-level cache](https://raw.githubusercontent.com/darkcar/hibernate-framework/master/note/Hibernate-first-level-cache.jpeg)
 
 ### Hibernate cache feature
 
 1. 持久态实体，会自动更新数据库
 
 ```java
 User user = session.get(User.class, 1);
 user.setUsername("FrankandLiyi");
 ```
 
 We don't need to call function session.update(user), the data in db has been updated.  (11)
 
 
 
 
 
 
 
 
 
 
 