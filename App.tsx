import {Button, SafeAreaView} from 'react-native';
import CalendarModule from './src/CalendarModule';

function App(): React.JSX.Element {
  const handlePress = () => {
    CalendarModule.createCalendarEvent('testName', 'testLocation');
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
