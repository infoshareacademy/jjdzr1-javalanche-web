<%@ page import="java.time.LocalDate" %>
<form action="/addHolidayRequest" method="post">
    <div class="col-md-3">
        <div class="form-group col">
            <label for="startDay">First day:</label>
            <input name="holidayFirstDay" type="date" placeholder="YYYY-MM-DD" class="form-control form-field-width form-group" id="startDay"
                   value="<%=LocalDate.now().toString()%>"
                   min="<%=LocalDate.now().toString()%>" max="<%=LocalDate.now().plusDays(366).toString()%>">
        </div>
        <div class="form-group col">
            <label for="endDay">Last day:</label>
            <input name="holidayLastDay" type="date" class="col form-control form-field-width form-group" id="endDay"
                   value="<%=LocalDate.now().toString()%>"
                           min="<%=LocalDate.now().toString()%>" max="<%=LocalDate.now().plusDays(366).toString()%>">
        </div>
        <div class="button-container">
            <button class="button-position btn btn-dark" type="submit">Submit
            </button>
            <button class="button-position btn btn-dark" type="reset">Reset
            </button>
        </div>
    </div>
</form>