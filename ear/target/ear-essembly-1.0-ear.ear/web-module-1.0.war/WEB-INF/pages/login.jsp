<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>

<html>
    <title>Admin</title>
    <body>
    <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Please Sign In</h3>
            </div>
            <div class="panel-body">
                <form role="form" action="<c:url value="/j_security_check"/>" method="post">
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="E-mail" name="j_username" id="user_login">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Password" type="password" id="user_pass" name="j_password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input name="remember" type="checkbox" value="Remember Me">Remember Me
                            </label>
                        </div>
                        <!-- Change this to a button or input when using this as a form -->
                        <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
