<#-- @ftlvariable name="authenticated" type="boolean" -->
<#import "lib/html.ftl" as H>
<@H.html>
    <@H.head "Home"></@H.head>
    <@H.body>
        <#if authenticated>
            <p>You are authenticated</p>
            <a href="/chat">chat</a>
            <a href="/signOut">sign out</a>
        <#else>
            <p>You are not authenticated</p>
            <a href="/signIn">sign in</a>
            <a href="/signUp">sign up</a>
        </#if>
    </@H.body>
</@H.html>