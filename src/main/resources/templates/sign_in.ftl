<#import "lib/html.ftl" as H>
<@H.html>
    <@H.head "Sign in"></@H.head>
    <@H.body>
        <div>
            <a href="/">home</a>
            <a href="/signUp">sign up</a>
        </div>
        <div>
            <form action="/signIn" method="post" accept-charset="UTF-8">
                <input type="text" name="username" placeholder="username">
                <input type="password" name="password" placeholder="password">
                <input type="submit" value="sign in">
            </form>
        </div>
    </@H.body>
</@H.html>