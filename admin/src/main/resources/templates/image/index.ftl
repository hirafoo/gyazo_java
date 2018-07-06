<#import "/layout/default.ftl" as layout>
<@layout.myLayout>

<#list images as x>
  <a href="/image/detail/${x.id}"> ${x.id} </a> ${x.hash} ${x.createdAt} <br>
</#list>

</@layout.myLayout>
