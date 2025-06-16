<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <div class="container-fluid">
        <!-- 브랜드 로고 -->
        <a class="navbar-brand" href="#">
            <i class="fa-solid fa-house"></i> Backend
        </a>

        <!-- 모바일 햄버거 메뉴 -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <!-- 좌측 메뉴 -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/board/list">게시판</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">메뉴2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">메뉴3</a>
                </li>
            </ul>

            <!-- 우측 메뉴 -->
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <img src="https://randomuser.me/api/portraits/men/12.jpg" class="avatar-sm"/>
                        admin
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa-solid fa-right-from-bracket"></i> 로그아웃
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>