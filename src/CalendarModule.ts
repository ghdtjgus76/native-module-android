import {NativeModules} from 'react-native';

interface CalendarModuleInterface {
  createCalendarEvent(
    name: string,
    location: string,
    myFailureCallback: (error: Error) => void,
    mySuccessCallback: (eventId: number) => void,
  ): void;
}

const {CalendarModule} = NativeModules;

export default CalendarModule as CalendarModuleInterface;
