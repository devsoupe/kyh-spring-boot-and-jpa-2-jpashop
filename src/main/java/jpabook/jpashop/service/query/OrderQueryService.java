package jpabook.jpashop.service.query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// OSIV 끄고 관리하기 위한 서비스를 추가
@Service
@Transactional(readOnly = true)
public class OrderQueryService {
}
