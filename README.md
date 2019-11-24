# DistributedMall
Project for course software engineering

# modules
- common   
    common dependency, such as beans, utils and mybatis generator
- dao  
    mapper
- service  
    service code


## notes on adding mybatis generator's generate command to the run menu for intellij idea

1. click the run target menu, select `Edit Configurations..`
2. Then a panel will be open. click the `+` button in the left up corner
3. choose maven. Then what you need to do is just to fill the parameters
4. The name option's value will be your command's name
5. For working directory, choose the absolute path of common module's root 
path in your computer
6. The most and last step, fill the Command line with `mybatis-generator:generate`
then click apply. 

## 模块
- 用户模块
- 商品模块
- 购物车模块
- 订单配送模块


##  更改
- 删除找回密码问题和答案