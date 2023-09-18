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
                            <form:form action="${formUrl}" id="importForm" commandName="command" enctype="multipart/form-data">
                                <input type="file" name="fileUpload" id="inputFileExcel" accept=".xlsx">
                                <button type="submit" class="btn btn-primary waves-effect waves-light" id="btnSave" href="#">
                                    <i class="fa fa-save"></i>&nbsp;<fmt:message key="label.save"/>
                                </button>
                                <input type="hidden" name="crudaction" id="crudaction" value="upload-file" />
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {

        });
    </script>
</body>
</html>