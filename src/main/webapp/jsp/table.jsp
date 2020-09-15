                    <tbody id="calendarTable">
                    <tr>
                        <th scope="col" class="m-0 p-0"
                            style="vertical-align: middle; text-align: end; font-size: x-small">
                            <button type="button" class="btn btn-outline-danger rounded-0 m-0 p-0" data-toggle="modal" data-target=<%=modalUserId %>
                                    style="vertical-align: middle; text-align: end; font-size: small; width: 100px; height: 50px">
                                <p style="margin-top: auto; margin-bottom: auto"><%= user.getFirstName()%>
                                </p>
                                <p style="margin-top: auto; margin-bottom: auto"><%= user.getLastName()%>
                                </p>
                            </button>
                            <div id="<%=modalUserId%>" class="modal fade" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <%--                                            <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                                            <h4 class="modal-title">Employee overview</h4>
                                        </div>
                                        <div class="modal-body" style="vertical-align: middle; text-align: left; font-size: medium;">
                                            <p>Employee Id: <%= userRepository.getUsersList().get(user.getId()-1).getId() %></p>
                                            <p>Name: <%= userRepository.getUsersList().get(user.getId()-1).getFirstName() %></p>
                                            <p>Last name: <%= userRepository.getUsersList().get(user.getId()-1).getLastName() %></p>
                                            <p>Email address: <%= userRepository.getUsersList().get(user.getId()-1).getEmail() %></p>
                                            <p>Number of remaining days off: <%= userRepository.getUsersList().get(user.getId()-1).getDaysOffLeft() %></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                                <%
                                    for (LocalDate localDate: dateList) {

                                        boolean isNationalHoliday = false;

                                        for(Holidays nationalHoliday : HolidaysJsonData.returnOnlyHolidaysAsList()){
                                            if (nationalHoliday.getHolidayDateInLocalDateFormat().equals(localDate)){
                                                isNationalHoliday = true;
                                            }
                                        }

                                        if (userListMap.get(user).contains(localDate)) {
                                %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-success rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset">Day off
                            </button>
                        </th>
                        <%
                        } else if (localDate.getDayOfWeek().toString().equalsIgnoreCase("saturday")
                                || localDate.getDayOfWeek().toString().equalsIgnoreCase("sunday")
                                || isNationalHoliday) {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"
                                    disabled><%= localDate%>

                            </button>
                        </th>
                        <% } else {
                        %>
                        <th scope="col" class="m-0 p-0">
                            <button type="button" class="btn btn-secondary rounded-0 m-0 p-0"
                                    data-toggle="modal" data-target=".modalDay"
                                    style="width: 70px; height: 50px; font-size: xx-small; padding: unset"><%= localDate%>
                            </button>
                        </th>
                        <%
                                }
                            }
                        %>
                        </th>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>