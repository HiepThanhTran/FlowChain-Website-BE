<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container list">
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center list__title">Danh sách đánh giá</h1>
        <a href="<c:url value="/admin/ratings/add"/>" class="list__icon-add">
            <i class='bx bxs-plus-circle'></i>
        </a>
    </div>
</div>


<div class="container mt-4">
    <table id="table" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Người đánh giá</th>
            <th>Nội dung</th>
            <th>Tiêu chuẩn</th>
            <th>Đánh giá</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhập</th>
            <th>Active</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="rating" items="${ratings}">
            <tr id="item${rating.id}">
                <td>${rating.id}</td>
                <td>${rating.supplier.name}</td>
                <td>${rating.content}</td>
                <td>${rating.criteria}</td>
                <td>${rating.rating}</td>
                <td>
                    <fmt:parseDate value="${ rating.createdAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:if test="${ rating.updatedAt != null }">
                        <fmt:parseDate value="${ rating.updatedAt }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdatedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedUpdatedDateTime }"/>
                    </c:if>
                    <c:if test="${ rating.updatedAt == null }">
                        Chưa cập nhập
                    </c:if>
                </td>
                <td>${rating.active}</td>
                <td>
                    <a class="btn btn-primary btn-sm" href="<c:url value="/admin/ratings/edit/${rating.id}"/>">
                        <i class='bx bxs-edit'></i>
                    </a>

                    <c:url value="/admin/ratings/delete/${rating.id}" var="deleteRating"/>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem('${deleteRating}', ${rating.id})">
                        <i class='bx bx-x'></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>