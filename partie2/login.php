<?php
//connexion 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);
//variables
$email = $_POST["email"];
$password = $_POST["password"];
$sql = "select id from users where email = '".$email."' and password = '".$password."';";
$result = mysqli_query($con,$sql);
$response = array();
if (mysqli_num_rows($result)>0)
{
	 
    $row = mysqli_fetch_row($result);	 
	$user_id = $row[0];	
	
	$code = "login_success";
	array_push($response,array("code"=>$code,"user_id"=>$user_id));
	echo json_encode($response);
	
}
else 
{
	$code = "login_failed";
	$message = "user not found...please try again";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
	
	
}
mysqli_close($con);




?>