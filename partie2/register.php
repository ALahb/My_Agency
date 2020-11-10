<?php
//connexion 
$user_name = "root";
$password = "";
$host = "localhost";
$db_name = "pno";
$con = mysqli_connect($host,$user_name,$password,$db_name);
//variables
$name = $_POST["firstname"];
$prenom = $_POST["lastname"];
$email = $_POST["email"];
$password = $_POST["password"];
$sql = "select * from users where email like '".$email."';";

//execute the query 
$result = mysqli_query($con,$sql);
 
$response = array();
if ((mysqli_num_rows($result)>0))
{
	$code = "reg_failed";
	$message = "E-mail already exists";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
	
}

else 
{
$sql = "insert into users values ('','".$name."','".$prenom."','".$email."','".$password."');";	
$result = mysqli_query($con,$sql);
$code = "reg_success";
	$message = "thank you for register with us";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
}
mysqli_close($con);



?>