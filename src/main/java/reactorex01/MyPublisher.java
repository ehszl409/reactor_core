package reactorex01;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

// 출판사
// Publisher의 책임 : 구독을 관리
// <T> : 발행 할 데이터의 타입
public class MyPublisher implements Publisher<Integer>{
	
	// its = DB데이터를 임의로 만들어준 객체
	Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	
	// subscribe 함수를  Subscriber가 호출하면 Publisher를 구독할 수 있습니다.
	public void subscribe(Subscriber<? super Integer> s) {
		// TODO Auto-generated method stub
		System.out.println("1. MyPublisher : 구독을 제공 합니다.");
		
		// 구독자 마다 새로운 scription(구독 정보)을 만들어 줘야 한다. (응답)
		// 구독 정보를 만들어 준 다음 바로 구독 정보를 구독자에게 전달해 줍니다.
		// Subscription생성시에 Subscriber 정보를 넘겨줘야 
		// Subscriber에게 구독 정보를 전달 할 수있습니다.
		// its의 데이터를 구독 하게 된것이고 요청하면 그에 맞는 데이터를 Subscriber에게 전달합니다.
		s.onSubscribe(new MySubscription((MySubscriber) s, its));
	}

}
