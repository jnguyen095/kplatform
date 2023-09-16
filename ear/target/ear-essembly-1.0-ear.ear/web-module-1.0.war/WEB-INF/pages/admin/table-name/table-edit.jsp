<%@ include file="/common/taglibs.jsp" %><html>
<head>
    <title><fmt:message key="admin.user.edit"/> </title>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="admin.user.title"/></h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><fmt:message key="form.input"/></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <c:url value="/admin/table-name/edit.html" var="formUrl"/>
                            <form:form action="${formUrl}" commandName="item">
                                <div class="form-group">
                                    <label><fmt:message key="tablename.name"/></label>
                                    <input name="pojo.tableName" value="${item.pojo.tableName}" class="form-control">
                                    <form:errors path="pojo.tableName" cssClass="error-inline"/>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key="areaname.name"/></label>
                                    <form:select path="pojo.areaName.areaNameId" cssClass="form-control">
                                        <form:option value=""><fmt:message key="areaname.select"/></form:option>
                                        <form:options items="${areaNames}" itemLabel="areaName" itemValue="areaNameId"/>
                                    </form:select>
                                    <form:errors path="pojo.areaName.areaNameId" cssClass="error-inline"/>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key="tablename.status"/></label>

                                    <label class="radio-inline">
                                        <form:radiobutton path="pojo.status" value="1" checked="${(item.pojo.status == null or item.pojo.status) ? 'checked' : ''}"/>&nbsp;
                                        <fmt:message key="tablename.active"/>
                                        &nbsp;
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton path="pojo.status" value="0" checked="${!item.pojo.status ? 'checked' : ''}"/>&nbsp;
                                        <fmt:message key="tablename.inactive"/>
                                        &nbsp;
                                    </label>
                                </div>

                                <input type="hidden" name="crudaction" value="insert-update"/>
                                <a href="<c:url value="/admin/table-name/list.html"/>" class="btn btn-default"><fmt:message key="button.back"/></a>
                                <button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
                                <input type="hidden" name="pojo.tableNameId" value="${item.pojo.tableNameId}">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>