import {Button, NativeModules, SafeAreaView} from 'react-native';

function App(): React.JSX.Element {
  const {CalendarModule} = NativeModules;

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
