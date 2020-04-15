<#-- @ftlvariable name="rooms" type="java.util.List<ru.itis.chat.websockets.dto.ChatRoomIndexDto>" -->
<#import "lib/html.ftl" as H>
<@H.html>
    <@H.head "Chat"></@H.head>
    <@H.body>
        <form action="/chat" method="post" accept-charset="UTF-8">
            <input type="text" name="name" placeholder="room name">
            <input type="submit" value="create chat room">
        </form>
        <hr>
        <#list rooms as room>
            <a href="/chat/${room.token}">&gt; ${room.name}</a>
            <br>
        </#list>
    </@H.body>
</@H.html>