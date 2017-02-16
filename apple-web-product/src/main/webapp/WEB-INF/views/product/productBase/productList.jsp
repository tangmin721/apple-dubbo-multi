<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
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
            $('#but_product_add').on('click',function () {
                window.location.href='/product/productBase/productForm';
            })
        })
    </script>
</head>
<body>
<div>
    <div>
        <button type="button" id="but_product_add" >添加</button>
    </div>
</div>

<div id="product_table">
    <table  data-width="100%">
        <thead>
        <tr>
            <th width="26"><input type="checkbox"></th>
            <th width="26" title="序号">No.</th>
            <th align="center" data-product-field="name" title="name">name</th>
            <th align="center" data-product-field="price" title="price">price</th>
            <th align="center" data-product-field="memo" title="memo">memo</th>
            <th align="center" data-product-field="uid" title="uid">uid</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="#{page.list}" varStatus="index">
            <tr data-id="${item.id }">
                <td><input type="checkbox" name="ids" value="${item.id }"></td>
                <td>${index.count+page.pageStart}</td>
                <td>${item.name }</td>
                <td>${item.price }</td>
                <td>${item.memo }</td>
                <td>${item.uid }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
