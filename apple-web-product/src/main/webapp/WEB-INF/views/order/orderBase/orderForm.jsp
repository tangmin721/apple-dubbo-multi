<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OrderList</title>
    <script src="/static/BJUI/plugins/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#but_order_add').on('click',function () {
                window.location.href='/order/orderBase/orderForm';
            })
        })
    </script>
</head>
<body>
<form action="/order/orderBase/saveOrder" method="post">
    <input type="hidden" name="id" value="${entity.id}">
    <input type="hidden" name="version" value="${entity.version}">
    <table>
        <tbody>
        <tr>
            <td>
                <label for="ono"><span class="red">*</span>ono：</label>
                <input type="text" name="ono" id="ono" value="${entity.ono }" data-rule="required" size="15">
            </td>
        </tr>
        <tr>
            <td>
                <label for="uid"><span class="red">*</span>uid：</label>
                <input type="text" name="uid" id="uid" value="${entity.uid }" data-rule="required" size="15">
            </td>
        </tr>
        <tr>
            <td>
                <label for="totalPrice"><span class="red">*</span>totalPrice：</label>
                <input type="text" name="totalPrice" id="totalPrice" value="${entity.totalPrice }" data-rule="required" size="15">
            </td>
        </tr>
        </tbody>
    </table>
    <button type="submit">提交</button>
</form>

</body>
</html>
