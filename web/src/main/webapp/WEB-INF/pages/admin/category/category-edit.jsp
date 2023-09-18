<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="admin.category.edit"/> </title>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="admin.category.edit"/></h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><fmt:message key="form.input"/></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <c:url value="/admin/category/edit.html" var="formUrl"/>
                            <form:form action="${formUrl}" commandName="command">
                                <div class="treeview w-20 border">
                                    <h6 class="pt-3 pl-3">Folders</h6>
                                    <hr>
                                    <ul class="mb-1 pl-3 pb-2">
                                        <li><i class="fas fa-angle-right rotate"></i>
                                            <span><i class="far fa-envelope-open ic-w mx-1"></i>Mail</span>
                                            <ul class="nested">
                                                <li><i class="far fa-bell ic-w mr-1"></i>Offers</li>
                                                <li><i class="far fa-address-book ic-w mr-1"></i>Contacts</li>
                                                <li><i class="fas fa-angle-right rotate"></i>
                                                    <span><i class="far fa-calendar-alt ic-w mx-1"></i>Calendar</span>
                                                    <ul class="nested">
                                                        <li><i class="far fa-clock ic-w mr-1"></i>Deadlines</li>
                                                        <li><i class="fas fa-users ic-w mr-1"></i>Meetings</li>
                                                        <li><i class="fas fa-basketball-ball ic-w mr-1"></i>Workouts</li>
                                                        <li><i class="fas fa-mug-hot ic-w mr-1"></i>Events</li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><i class="fas fa-angle-right rotate"></i>
                                            <span><i class="far fa-folder-open ic-w mx-1"></i>Inbox</span>
                                            <ul class="nested">
                                                <li><i class="far fa-folder-open ic-w mr-1"></i>Admin</li>
                                                <li><i class="far fa-folder-open ic-w mr-1"></i>Corporate</li>
                                                <li><i class="far fa-folder-open ic-w mr-1"></i>Finance</li>
                                                <li><i class="far fa-folder-open ic-w mr-1"></i>Other</li>
                                            </ul>
                                        </li>
                                        <li><i class="fas fa-angle-right rotate"></i>
                                            <span><i class="far fa-gem ic-w mx-1"></i>Favourites</span>
                                            <ul class="nested">
                                                <li><i class="fas fa-pepper-hot ic-w mr-1"></i>Restaurants</li>
                                                <li><i class="far fa-eye ic-w mr-1"></i>Places</li>
                                                <li><i class="fas fa-gamepad ic-w mr-1"></i>Games</li>
                                                <li><i class="fas fa-cocktail ic-w mr-1"></i>Coctails</li>
                                                <li><i class="fas fa-pizza-slice ic-w mr-1"></i>Food</li>
                                            </ul>
                                        </li>
                                        <li><i class="far fa-comment ic-w mr-1"></i>Notes</li>
                                        <li><i class="fas fa-cogs ic-w mr-1"></i>Settings</li>
                                        <li><i class="fas fa-desktop ic-w mr-1"></i>Devices</li>
                                        <li><i class="fas fa-trash-alt ic-w mr-1"></i>Deleted Items</li>
                                    </ul>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value="/layout/bootstrap/mdb.min.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/layout/bootstrap/mdb.min.css"/>" type="text/css"></script>
    <script>
        $(document).ready(function() {
            $('.treeview').mdbTreeview();
        });
    </script>
</body>
</html>