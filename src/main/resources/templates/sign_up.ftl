<#import "lib/html.ftl" as H>
<@H.html>
    <@H.head "Sign up"></@H.head>
    <@H.body>
        <div>
            <a href="/">home</a>
            <a href="/signIn">sign in</a>
        </div>
        <div>
            <form action="/signUp" method="post" accept-charset="UTF-8">
                <input type="text" name="username" placeholder="username">
                <input type="password" name="password" placeholder="password">
                <input type="submit" value="sign up">
            </form>
        </div>
    </@H.body>
</@H.html>