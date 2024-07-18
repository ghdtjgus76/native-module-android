import {useEffect} from 'react';
import {Button, SafeAreaView} from 'react-native';
import {CalendarEventEmitter, CalendarModule} from './src/CalendarModule';
import {ImagePickerModule} from './src/ImagePickerModule';
import {PhoneModule} from './src/PhoneModule';

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

  const handlePressCalendar = async () => {
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

  const handlePressImagePicker = async () => {
    try {
      const image = await ImagePickerModule.pickImage();
      console.log(image);
    } catch (e) {
      console.error(e);
    }
  };

  const handlePressPhone = async () => {
    const result = await PhoneModule.makeCall('010-8712-0786');
    console.log(result);
  };

  return (
    <SafeAreaView>
      <Button
        title="Click to invoke your native calendar module!"
        color="#841584"
        onPress={handlePressCalendar}
      />
      <Button
        title="Click to invoke your native image picker module!"
        color="#841584"
        onPress={handlePressImagePicker}
      />
      <Button
        title="Click to invoke your native phone module!"
        color="#841584"
        onPress={handlePressPhone}
      />
    </SafeAreaView>
  );
}

export default App;
