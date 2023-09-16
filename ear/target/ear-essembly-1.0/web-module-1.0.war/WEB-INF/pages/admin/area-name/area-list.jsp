<%@ include file="/common/taglibs.jsp" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <title><fmt:message key="areaname.title"/> </title>
</head>
<body>
    <h1 class="page-header"><fmt:message key="areaname.title"/></h1>
    <div class="dataTable_wrapper">
        <div class="row">
            <div class="col-sm-12">
                <c:if test="${!empty messageResponse}">
                    <div class="alert alert-success">${messageResponse}</div>
                </c:if>
                <c:url var="formUrl" value="/admin/area-name/list.html"/>
                <div class="buttons">
                    <a href="<c:url value="/admin/area-name/edit.html"/>"><button type="button" class="btn btn-sm btn-primary"><fmt:message key="button.add"/> </button></a>
                </div>
                <form:form action="${formUrl}" commandName="items" role="form" id="areaNameForm" cssClass="form-horizontal">
                    <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
                                   partialList="true" sort="external" size="${items.totalItems}" defaultsort="2"
                                   id="tableList" excludedParams="checkList"
                                   pagesize="${items.maxPageItems}" export="false" class="table table-striped table-bordered table-hover dataTable no-footer">
                        <display:column headerClass="table_header sorting" property="areaName" sortName="areaName" sortable="true" titleKey="areaname.name" />
                        <display:column headerClass="table_header sorting" sortName="status" sortable="true" titleKey="areaname.status">
                             <c:choose>
                                <c:when test="${tableList.status}">
                                    <fmt:message key="areaname.active"></fmt:message>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="areaname.inactive"></fmt:message>
                                </c:otherwise>
                            </c:choose>
                        </display:column>

                        <display:column headerClass="col-actions" class="col-actions" titleKey="label.action">
                            <a href="<c:url value="/admin/area-name/edit.html?pojo.areaNameId=${tableList.areaNameId}"/>"> <i class="fa fa-edit"></i></a>
                        </display:column>

                        <display:setProperty name="paging.banner.item_name" value="table"/>
                        <display:setProperty name="paging.banner.items_name" value="tables"/>
                        <display:setProperty name="paging.banner.placement">bottom</display:setProperty>
                    </display:table>
                </form:form>
            </div>
        </div>
    </div>


</body>
</html>