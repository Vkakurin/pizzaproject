
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>


   <h4>Add new user</h4>
    ${message?if_exists}
    <@l.login "/registration" />
</@c.page>
<a href="/main">Go to Main page</a>




















<#--<!DOCTYPE html>-->
<#--<html  xmlns="http://www.w3.org/1999/xhtml" >-->
<#--    <head>-->
<#--        <title>Spring Security Example </title>-->
<#--    </head>-->
<#--    <body>-->
<#--    <a href="/main">Go to Main page</a>-->
<#-- <h4>Add new User</h4>-->
<#--  {{#message}}-->
<#--      {{message}}-->
<#--  {{/message}}-->
<#--        <form  method="post" action="/registration">-->
<#--          <div><label> User Name : <input type="text" name="username" placeholder="username" /> </label></div>-->
<#--          <div><label> Password: <input type="password" name="password" placeholder="password"/> </label></div>-->
<#--            <div><label> Email: <input type="email" name="email" class="form-control" placeholder="some@some.com"/> </label></div>-->
<#--          <div><input type="submit" value=" Sign In"/></div>-->
<#--        </form>-->


<#--    </body>-->
<#--</html>-->