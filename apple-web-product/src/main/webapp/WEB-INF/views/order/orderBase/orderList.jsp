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
<div>
    <div>
        <button type="button" id="but_order_add" >添加</button>
    </div>
</div>

<div id="order_table">
    <table  data-width="100%">
        <thead>
        <tr>
            <th width="26"><input type="checkbox"></th>
            <th width="26" title="序号">No.</th>
            <th align="center" data-order-field="ono" title="ono">ono</th>
            <th align="center" data-order-field="uid" title="uid">uid</th>
            <th align="center" data-order-field="totalPrice" title="totalPrice">totalPrice</th>
            <th align="center" data-order-field="status" title="status">status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="#{page.list}" varStatus="index">
            <tr data-id="${item.id }">
                <td><input type="checkbox" name="ids" value="${item.id }"></td>
                <td>${index.count+page.pageStart}</td>
                <td>${item.ono }</td>
                <td>${item.uid }</td>
                <td>${item.totalPrice }</td>
                <td>${item.status }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
