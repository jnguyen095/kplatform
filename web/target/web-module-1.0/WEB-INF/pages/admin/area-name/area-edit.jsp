<%@ include file="/common/taglibs.jsp" %><html>
<head>
    <title><fmt:message key="areaname.edit"/> </title>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="areaname.title"/></h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><fmt:message key="form.input"/></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <c:url value="/admin/area-name/edit.html" var="formUrl"/>
                            <form:form action="${formUrl}" commandName="item">
                                <div class="form-group">
                                    <label><fmt:message key="areaname.name"/></label>
                                    <input name="pojo.areaName" value="${item.pojo.areaName}" class="form-control">
                                    <form:errors path="pojo.areaName" cssClass="error-inline"/>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key="areaname.status"/></label>

                                    <label class="radio-inline">
                                        <form:radiobutton path="pojo.status" value="1" checked="${(item.pojo.status eq null or item.pojo.status) ? 'checked' : ''}"/>&nbsp;
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
                                <a href="<c:url value="/admin/area-name/list.html"/>" class="btn btn-default"><fmt:message key="button.back"/></a>
                                <button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
                                <input type="hidden" name="pojo.areaNameId" value="${item.pojo.areaNameId}">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>