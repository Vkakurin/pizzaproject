<#import "parts/common.ftlh" as c>

<@c.page>
    List of users
    <a href="/main">Go to Main page</a>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.user_id}">edit</a></td>
            </tr>
        </#list>
        </tbody>


    <table>
        <thead>
        <tr>

            <th>UserName</th>
            <th>Password</th>
            <th>UserId</th>
            <th>ActivationCode</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.user_id}</td>
                <td>${user.getCodeForUserList()}</td>
                <td>${user.getEmailForUserList()}</td>
            </tr>
        </#list>
        </tbody>



    </table>
    <form method="get" action="/user/deleteUser" >
        <input type="" name="user_id"  placeholder="Enter userId" >
        <button type="submit">Delete User by Id</button>
    </form>
</@c.page>