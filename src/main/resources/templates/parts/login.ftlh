<#macro login path>
    <form  method="post" action="${path}">
        <div><label> User Name : <input type="text" name="username" placeholder="username" /> </label></div>
        <div><label> Password: <input type="password" name="password" placeholder="password"/> </label></div>
        <div><label> Password: <input type="password" name="password2" placeholder="password"/> </label></div>
        <div><label> Email: <input type="email" name="email" class="form-control" placeholder="some@some.com"/> </label></div>
        <div><input type="submit" value=" Sign In"/></div>
    </form>
</#macro>