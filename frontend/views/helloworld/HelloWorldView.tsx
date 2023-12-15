import { HelloWorldService } from 'Frontend/generated/endpoints.js';
import { useEffect, useState } from 'react';

export default function HelloWorldView() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const subscription = HelloWorldService.countForever()
    subscription.onNext(setCount);

    return () => subscription.cancel();
  }, []);

  return (
    <div className='p-m'>{count}</div>
  )
}
