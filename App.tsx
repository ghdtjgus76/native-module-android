import {Button, SafeAreaView} from 'react-native';
import CalendarModule from './src/CalendarModule';

function App(): React.JSX.Element {
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
