package reactorex01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// <T> 구독 할 데이터 타입.
// 구독자.
public class MySubscriber implements Subscriber<Integer>{
	
	// size : Back Pressure의 변수 설정
	private Integer size = 3; 
	// Back Pressure.nobounded : size = 1000000000....
	//  = 제한 값을 무한으로 하는 것.
	// 동영상 스트리밍을 한다고 가정하면 최고의 서비스를 제공하기 위해선 동영상의
	// 끊김이 없어야 한다. 그래서 Back Pressure.nobounded로 설정해서
	// 받을 수 있는 데이터를 최대한 받아서 버퍼에 담아두면 동영상의 끊김을 줄여서
	// 서비스의 질을 높일 수 있다.
	
	// s : 구독 정보를 전달하는 참조 변수.
	private Subscription s;
	
	// onSubscribe 함수는 Subscriber가 구독을 하면
	// Publisher가 구독 정보를 만들고 난 후 Subscriber에게 전달할 때 호출되는 함수 입니다.
	public void onSubscribe(Subscription s) {
		this.s = s;
		
		System.out.println("3. MyPublisher : 구독 정보를 돌려 받음.");
		
		// 구독된 데이터를 요청함.(신문을 요청)
		// size : 내가 받을 수 있는 구독 데이터의 최대치 설정해서 요청합니다. (Back Pressure)
		// (Back Pressure는 Publisher도 보내는 데이터를 제한할 때 사용할 수 있습니다.)
		s.request(size);
		
		
	}

	// onNext : 구독된 데이터를 받는 함수 입니다.
	public void onNext(Integer t) {
		System.out.println("OnNext : 신문(구독 데이터)" + t);
		size--;
		if(size == 0) {
			size = 3;
			s.request(size);
		}
		
	}

	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	// onComplete : 구독된 데이터를 다 받으면 실행됩니다.  
	public void onComplete() {
		System.out.println("구독 데이터 모두 받았습니다.");
		
	}

	
}
