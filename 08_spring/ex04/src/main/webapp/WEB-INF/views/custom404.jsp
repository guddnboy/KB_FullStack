<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>페이지를 찾을 수 없습니다</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 100px auto;
            max-width: 600px;
        }
        .error-code {
            font-size: 72px;
            color: #e0e0e0;
            margin-bottom: 20px;
        }
        .error-message {
            font-size: 24px;
            color: #333;
            margin-bottom: 30px;
        }
        .home-link {
            display: inline-block;
            padding: 12px 24px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .home-link:hover { background: #0056b3; }
    </style>
</head>
<body>
<div class="error-code">404</div>
<div class="error-message">
    해당 URL(${uri})은 존재하지 않습니다.
</div>
<a href="/" class="home-link">홈으로 돌아가기</a>
</body>
</html>
