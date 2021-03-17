package reactorex01;

import java.util.Iterator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 구독 정보
public class MySubscription implements Subscription{
	
	// subscriber에게 구독 데이터 정보를 돌려줘야 하기에
	// 구독자 객체를 알고 있어야 한다. 
	private final MySubscriber subscriber;
	private Iterator<Integer> it;
	
	// Subscription은 구독 정보를 가지고 있다가 구독자에게 전달 해줘야 하기에
	// Subscriber과 (임의로 만든)DB정보를 생성자 호출시 넣어 줘야 합니다.
	public MySubscription(MySubscriber s, Iterable<Integer> its) {
		System.out.println("2. MyPublisher : 구독 정보를 생성 합니다.");
		this.subscriber = s;
		this.it = its.iterator();
	}

	// request : Subscriber가 구독 정보을 읽어보고 구독 데이터를 요청 합니다.
	public void request(long n) { // n = 3
		System.out.println("4. MyPublisher : " + n + "개씩 받습니다.");
		
		
		// n개씩 구독자 한테 돌려주는 로직이 필요함.
		// Sink의 일정 부분을 직접 구현하는 로직입니다.
		// 목표 : 구독자가 원하는 Back Pressure만큼 데이터를 전달해주기.
		while (n > 0) {
			// it.hasNext() : 데이터가 있는지 확인.
			if(it.hasNext()) { 
				// onNext : 함수로 구독자에게 구독 데이터를 전달해 줍니다.
				// it.next() : 다음 데이터를 순차적으로 찾아서 리턴.
				subscriber.onNext(it.next()); 	
			} else {
				// (임의로만든)DB에 데이터가 없으면 구독자에게
				// 구독 데이터를 다 전달했다고 onComplete() 함수를 호출 해줍니다.
				subscriber.onComplete();
				break;
			}
			
			n--;
			
		} // end of while
		
		System.out.println("========================");
		
		
	}

	public void cancel() {
		// TODO Auto-generated method stub
		
	}

}
