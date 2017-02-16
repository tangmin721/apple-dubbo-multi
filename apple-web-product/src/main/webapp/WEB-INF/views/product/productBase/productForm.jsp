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
<form action="/product/productBase/saveProduct" method="post">
    <input type="hidden" name="id" value="${entity.id}">
    <input type="hidden" name="version" value="${entity.version}">
    <table>
        <tbody>
        <tr>
            <td>
                <label for="name"><span class="red">*</span>name：</label>
                <input type="text" name="name" id="name" value="${entity.name }" data-rule="required" size="15">
            </td>
        </tr>
        <tr>
            <td>
                <label for="price"><span class="red">*</span>price：</label>
                <input type="text" name="price" id="price" value="${entity.price }" data-rule="required" size="15">
            </td>
        </tr>
        <tr>
            <td>
                <label for="memo"><span class="red">*</span>memo：</label>
                <input type="text" name="memo" id="memo" value="${entity.memo }" data-rule="required" size="15">
            </td>
        </tr>
        <tr>
            <td>
                <label for="uid"><span class="red">*</span>uid：</label>
                <input type="text" name="uid" id="uid" value="${entity.uid }" data-rule="required" size="15">
            </td>
        </tr>
        </tbody>
    </table>
    <button type="submit">提交</button>
</form>

</body>
</html>
