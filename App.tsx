import {Button, SafeAreaView} from 'react-native';
import CalendarModule, {CalendarEventEmitter} from './src/CalendarModule';
import {useEffect} from 'react';

function App(): React.JSX.Element {
  useEffect(() => {
    const subscription = CalendarEventEmitter.addListener(
      'EventReminder',
      event => {
        console.log(event.eventProperty);
      },
    );

    () => {
      subscription.remove();
    };
  }, []);

  const handlePress = async () => {
    try {
      const eventId = await CalendarModule.createCalendarEvent(
        'Party',
        'My House',
      );
      console.log(`Created a new event with id ${eventId}`);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <SafeAreaView>
      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={handlePress}
      />
    </SafeAreaView>
  );
}

export default App;
