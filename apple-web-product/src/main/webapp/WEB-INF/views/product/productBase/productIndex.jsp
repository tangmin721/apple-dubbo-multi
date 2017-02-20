<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>productList</title>
    <script src="/static/BJUI/plugins/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#but_product_list').on('click',function () {
                window.location.href='/product/productBase/productList';
            });

            $('#but_product_getProductById').on('click',function () {
                window.location.href='/product/productBase/getProductById/2';
            });

            $('#but_product_getById').on('click',function () {
                $.ajax({
                    type: "POST",
                    url: "/product/productBase/getProductById/2",
                    dataType : "json",
                    success: function(data) {
                        console.log(data);
                        if(data.statusCode=='300'){
                            alert("系统调用出错了！");
                            console.log(data.message);
                        }else {
                            alert(data.data.id);
                        }
                    },
                    error :  function(){
                        alert("系统错误！");
                    }
                });
            });
        })
    </script>
</head>
<body>
<div>
    <button type="button" id="but_product_list" >List列表页</button><br>
    <button type="button" id="but_product_getById" >商品2</button><br>
    <a href="/product/productBase/getProductById/2">getProductById:2</a><br>

    测试 BaseFacade<br>

    <br>【新增方法（insert）】<br>
    <a href="/product/productBase/insert">insert:insert商品</a><br>

    <br>【查询方法（select）】<br>
    <a href="/product/productBase/selectById/4">selectById:4</a><br>
    <a href="/product/productBase/selectByIds">selectByIds:2,3,4</a><br>

    <br>【更新方法（update）】<br>
    <a href="/product/productBase/update">update:update商品</a><br>
    <a href="/product/productBase/getProductById/2">查询更新后的结果：price+1</a><br>

    <br>【删除方法（delete）】<br>
    <a href="/product/productBase/deleteById/3">deleted更新后的结果:3</a><br>
    <a href="/product/productBase/getProductById/2">查询deleted后的结果：delete_time</a><br>
    <a href="/product/productBase/deleteByIds">deleteByIds:2,3,4</a><br>

    <br>【查询list方法（remove）】<br>
    <a href="/product/productBase/selectList">selectList</a><br>
    <a href="/product/productBase/selectListPage">selectListPage</a><br>
    <a href="/product/productBase/selectListTotal">selectListTotal</a><br>
    <a href="/product/productBase/selectListPagination">selectListPagination</a><br>

    <br>【最后再测彻底删除（remove）】<br>
    <a href="/product/productBase/removeById/3">remove:3</a><br>
    <a href="/product/productBase/removeByIds">removeByIds:2,3,4</a><br>
    <a href="/product/productBase/selectListPagination">查询dremove后的结果</a><br>



</div>

</body>
</html>
