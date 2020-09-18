<form action="/forms" method="post">
    <label>Place holiday request</label>
    <div class="form-group col-md-6">
        <input class="form-control form-field-width form-group" type="date" id="startDay" name="trip-start"
               value="2018-07-22"
               min="2018-01-01" max="2018-12-31">
    </div>
    <div class="form-group col-md-6">
        <input class="col form-control form-field-width form-group" type="date" id="endDay" name="trip-end"
               value="2018-07-22"
               min="2018-01-01" max="2018-12-31">
    </div>
    <div class="button-container">
        <button class="button-position btn btn-dark" type="submit" class="btn btn-dark button-position">Submit
        </button>
        <button class="button-position btn btn-dark" type="reset" class="btn btn-dark button-position">Reset
        </button>
    </div>
</form>