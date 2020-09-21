<%@ page import="java.time.LocalDate" %>

<h1><%LocalDate.now();%> </h1>

<label>Place holiday request</label>
<form action="/forms" method="post">
    <div class="form-group col-md-4">
        <label for="startDay">Choice the first day of your leave:</label>
        <input class="form-control form-field-width form-group" type="date" id="startDay" name="trip-start"
               value="2018-07-22"
               min=<%LocalDate.now();%> max=<%LocalDate.now().plusDays(366);%>>
    </div>
    <div class="form-group col-md-4">
        <label for="endDay">Choice the last day of your leave:</label>
        <input class="col form-control form-field-width form-group" type="date" id="endDay" name="trip-end"
               value=<%LocalDate.now().toString();%>
                       min=<%LocalDate.now();%>  max=<%LocalDate.now().plusDays(366);%>>
    </div>
    <div class="button-container">
        <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit
        </button>
        <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset
        </button>
    </div>
</form>