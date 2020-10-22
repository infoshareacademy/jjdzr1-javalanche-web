<div style="margin: 10px">
    <h3>
        Team leader collapse
    </h3>
    <div class="accordion" id="accordionExample">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                    <button class="btn btn-outline-dark btn-block collapsed" type="button" data-toggle="collapse"
                            data-target="#collapseOne" aria-expanded="false" aria-controls="collapseTwo">
                        Manage holiday requests
                    </button>
                </h2>
            </div>
            <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <%@ include file="../forms/holidayForms/holidayRequestDecision.jsp" %>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h2 class="mb-0">
                    <button class="btn btn-outline-dark btn-block collapsed" type="button" data-toggle="collapse"
                            data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Add employees to a team
                    </button>
                </h2>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <%@ include file="../forms/teamsForms/addEmployeesToTeam.jsp" %>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                    <button class="btn btn-outline-dark btn-block collapsed" type="button" data-toggle="collapse"
                            data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Remove employees from a team
                    </button>
                </h2>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <%@ include file="../forms/teamsForms/removeEmployeesFromTeam.jsp" %>
                </div>
            </div>
        </div>
    </div>

</div>