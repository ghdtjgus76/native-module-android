import {NativeModules} from 'react-native';

interface CalendarModuleInterface {
  createCalendarEvent(name: string, location: string): Promise<number>;
}

const {CalendarModule} = NativeModules;

export default CalendarModule as CalendarModuleInterface;
