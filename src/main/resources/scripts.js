function userList() {
 // Call Web API to get a list of user
 $.ajax({
 url: 'http://localhost:8080/api/users/getall',
 type: 'GET',
 dataType: 'json',
 success: function (users) {
 userListSuccess(users);
 },
 error: function (request, message, error) {
 handleException(request, message, error);
 }
 });
}
function userListSuccess(users) {
 // Iterate over the collection of data
 $.each(users, function (index, user) {
 // Add a row to the user table
 userAddRow(user);
 });
}
function userAddRow(user) {
 // Check if <tbody> tag exists, add one if not
 if ($("#userTable tbody").length == 0) {
 $("#userTable").append("<tbody></tbody>");
 }
 // Append row to <table>
 $("#userTable tbody").append(
 userBuildTableRow(user));
}
function userBuildTableRow(user) {
 return "<tr>" +
 "<td>" + user.getFirstname + "</td>" +
 "<td>" + user.getLastname + "</td>" +
 "</tr>";
}
function handleException(request, message, error) {
 let msg = "";
 msg += "Code: " + request.status + "\n";
 msg += "Text: " + request.statusText + "\n";
 if (request.responseJSON != null) {
 msg += "Message" + request.responseJSON.Message + "\n";
 }
 alert(msg);
}
function formClear() {
 $("#firstname").val("");
 $("#lastname").val("");
}
function updateClick() {
 // Build user object from inputs
 const User = {};
 User.firstname=$("#firstname").val();
 User.lastname=$("#lastname").val();
 userAdd(User);
}
function userAdd(user) {
 $.ajax({
 url: "http://localhost:8080/api/users/postuser",
 type: 'POST',
 contentType:
 "application/json;charset=utf-8",
 data: JSON.stringify(user),
 success: function (user) {
 userAddSuccess(user);
 },
 error: function (request, message, error) {
 handleException(request, message, error);
 }
 });
}
function userAddSuccess(user) {
 userAddRow(user);
 formClear();
}

