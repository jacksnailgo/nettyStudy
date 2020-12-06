# nettyStudy
for netty Learning
12.7的计划
方向：
数据库已经连接正常， 数据库的缓存组件，入库和取数据之间添加缓存层，考虑使用Redis或者自己构建缓存层。
划分，缓存实体CacheEntity,那么我们的update操作，都是先对缓存进行更新了。


12.6思路
构建项目整体框架，内容包括：netty\net传输\文件配置加载\本地测试\数据库连接\Dao异步框架
1.添加同时整理POM文件，整合Hibernate,Spring,成功连接远程数据库。
2.实现异步同步数据库流程，多线程同步数据入库的操作。
3.控制台输入内容测试方法，代替Junit单元测试。


11.25思路
  处理将前端的URL信息解析方法名和参数，组装成一个AddressPoster ,里面包含方法名和地址.
游戏项目中，我们都是通过检索@PacketHandlers的类，得到每个类中的Packet，然后得到一个Map<每个Packet,包装类>
这个包装类包含了方法和@PacketHandler的类。   所以我们通过客户端获得这个Packet之后，我们就能检索得到调用他方法的service，method。
从而实现调用。

但是在这里，我用的是HttpRequest，天然带有method和Parameter。 需求是只需要标记@AddressHandler的类，和@Address的方法，我们就可以调用
调用过程是：  解析得到的Method和参数，执行器直接执行Method

而实际上，我们可以直接内部转发，并不需要预加载到缓存中，如果Method不是很多的情况下
