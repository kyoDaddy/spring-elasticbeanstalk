package com.kyo.springbootstart.process.user.controller;

import com.kyo.springbootstart.process.user.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * ViewResolver : 이름으로부터 실제로 사용할 뷰 객체를 결정하는 것
 *      - DispatcherServlet에게 뷰 정보를 전달하는 방법은 두 가지가 있는데,
 *          1) View 타입의 오브젝트를 주는 방법
 *          2) String 타입의 뷰 이름을 주는 방법
 *              - String 뷰 이름을 주는 경우 이름으로부터 실제로 사용할 뷰 객체를 결정해주는 뷰 리졸버가 필요하다.
 *              - 뷰 오브젝트를 넘겨주는 것 보다, 뷰 이름을 넘겨주어서 뷰 리졸버를 사용하는 것이 성능 면에서 유리 (뷰 리졸버는 보통 뷰 오브젝트를 캐싱하기 때문)
 *
 *      - 들어오는 요청의 Accept 헤더에 따라 응답이 달라진다.
 *      - Accept 헤더는 브라우저가 어떠한 타입의 본문을 응답을 원한다. 라고 서버한테 알려주는것 (Accept 헤더에 따라 응답이 달라질 수 있다.)
 *
 *      - 핵심정리
 *          1) 요청이 들어오면 그 요청에 응답을 만들수 있는 모든 뷰를 찾아낸다.
 *          2) 최종적으로 뷰의 타입과 Accept 헤더랑 비교를 해서 선택을 한다.
 *             클라이언트가 이 뷰를 원했다고 생각을 해
 *             이 뷰를 리턴한다
 *
 *
 */
@RestController
public class UserController {

    @GetMapping("/users")
    public String userList() {
        return "user test";
    }

    @PostMapping("/users/create")
    public @ResponseBody User create(@RequestBody User user) {
        return user;
    }




}
