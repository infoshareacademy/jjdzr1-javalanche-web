<div class="container-fluid">
    <br>
    <h3>Holiday request</h3><br><br>

    <form method="get" action="/addholidayrequest">
        <div class="form-group row">
            <label for="firstDay" class="col-sm-1 col-form-label">First day:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" id="firstDay" name="firstDay">
            </div>
        </div>
        <div class="form-group row">
            <label for="lastDay" class="col-sm-1 col-form-label">Last day:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" id="lastDay"  name="lastDay">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputTypeOfLeave" class="col-sm-1 col-form-label">Type of leave:</label>
            <div class="col-sm-3">
                <select id="inputTypeOfLeave" class="form-control">
                    <option>Vacation leave</option>
                    <option>Maternity leave</option>
                    <option>Parental leave</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-5">
                <button type="submit" class="btn btn-primary">Send request</button>
            </div>
        </div>
    </form>
</div>