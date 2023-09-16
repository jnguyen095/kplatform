<%@ include file="/common/taglibs.jsp" %><html>
<head>
    <title><fmt:message key="retailer.title"/> </title>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="retailer.title"/></h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><fmt:message key="form.input"/></div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <c:url value="/sysadmin/retailer/edit.html" var="formUrl"/>
                            <form:form action="${formUrl}" commandName="item">
                                <div class="form-group">
                                    <label><fmt:message key="retailer.code"/></label>
                                    <input name="pojo.code" value="${item.pojo.code}" class="form-control">
                                    <form:errors path="pojo.code" cssClass="error-inline"/>
                                </div>
                                <div class="form-group">
                                    <label><fmt:message key="retailer.name"/></label>
                                    <input name="pojo.name" value="${item.pojo.name}" class="form-control">
                                    <form:errors path="pojo.name" cssClass="error-inline"/>
                                </div>
                                <div class="form-group">
                                    <label><fmt:message key="retailer.address"/></label>
                                    <input name="pojo.address" value="${item.pojo.address}" class="form-control">
                                    <form:errors path="pojo.address" cssClass="error-inline"/>
                                </div>
                                <div class="form-group">
                                    <label><fmt:message key="retailer.phone"/></label>
                                    <input name="pojo.phone" value="${item.pojo.phone}" class="form-control">
                                    <form:errors path="pojo.phone" cssClass="error-inline"/>
                                </div>


                                <div class="form-group">
                                    <label><fmt:message key="areaname.status"/></label>

                                    <label class="radio-inline">
                                        <form:radiobutton path="pojo.status" value="1" checked="${(item.pojo.status eq null or item.pojo.status) ? 'checked' : ''}"/>&nbsp;
                                        <fmt:message key="retailer.active"/>
                                        &nbsp;
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton path="pojo.status" value="0" checked="${!item.pojo.status ? 'checked' : ''}"/>&nbsp;
                                        <fmt:message key="retailer.inactive"/>
                                        &nbsp;
                                    </label>
                                </div>

                                <input type="hidden" name="crudaction" value="insert-update"/>
                                <a href="<c:url value="/sysadmin/retailer/list.html"/>" class="btn btn-default"><fmt:message key="button.back"/></a>
                                <button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
                                <input type="hidden" name="pojo.retailerId" value="${item.pojo.retailerId}">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>