<%@ include file="/common/taglibs.jsp" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <title><fmt:message key="retailer.title"/> </title>
</head>
<body>
    <h1 class="page-header"><fmt:message key="retailer.title"/></h1>
    <div class="dataTable_wrapper">
        <div class="row">
            <div class="col-sm-12">
                <c:if test="${!empty messageResponse}">
                    <div class="alert alert-success">${messageResponse}</div>
                </c:if>
                <c:url var="formUrl" value="/sysadmin/retailer/list.html"/>
                <div class="buttons">
                    <a href="<c:url value="/sysadmin/retailer/edit.html"/>"><button type="button" class="btn btn-sm btn-primary"><fmt:message key="button.add"/> </button></a>
                </div>
                <form:form action="${formUrl}" commandName="items" role="form" id="areaNameForm" cssClass="form-horizontal">
                    <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
                                   partialList="true" sort="external" size="${items.totalItems}" defaultsort="2"
                                   id="tableList" excludedParams="checkList"
                                   pagesize="${items.maxPageItems}" export="false" class="table table-striped table-bordered table-hover dataTable no-footer">
                        <display:column headerClass="table_header sorting" property="code" sortName="code" sortable="true" titleKey="retailer.code" />
                        <display:column headerClass="table_header sorting" property="name" sortName="name" sortable="true" titleKey="retailer.name" />
                        <display:column headerClass="table_header sorting" sortName="status" sortable="true" titleKey="retailer.status">
                             <c:choose>
                                <c:when test="${tableList.status}">
                                    <fmt:message key="retailer.active"></fmt:message>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="retailer.inactive"></fmt:message>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                        <display:column headerClass="table_header sorting" property="address" sortName="address" sortable="true" titleKey="retailer.address" />
                        <display:column headerClass="table_header sorting" property="phone" sortName="phone" sortable="true" titleKey="retailer.phone" />

                        <display:column headerClass="col-actions" class="col-actions" titleKey="label.action">
                            <a href="<c:url value="/sysadmin/retailer/edit.html?pojo.retailerId=${tableList.retailerId}"/>"> <i class="fa fa-edit"></i></a>
                        </display:column>

                        <display:setProperty name="paging.banner.item_name" value="retailer"/>
                        <display:setProperty name="paging.banner.items_name" value="retailers"/>
                        <display:setProperty name="paging.banner.placement">bottom</display:setProperty>
                    </display:table>
                </form:form>
            </div>
        </div>
    </div>


</body>
</html>