<?php
// setcookie('name', 'Vasya', time()-3600);
// setcookie('name');
session_start();
session_destroy();
// setcookie(session_name(), session_id(), time()-3600);