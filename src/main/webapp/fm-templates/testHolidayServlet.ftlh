<body>
    <div>
        This is the holidays test servlet.

        <#list holidays as holiday>
            ${holiday}
<#--            <li>Name: ${holiday.getName()}
                / Date: ${holiday.getHolidayDate().getHolidayDateTime().getDay()}
                ${holiday.getHolidayDate().getHolidayDateTime().getMonth()}
                ${holiday.getHolidayDate().getHolidayDateTime().getYear()}
            </li>-->
        </#list>

    </div>
</body>