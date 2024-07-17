import {NativeModules} from 'react-native';

interface ImagePickerModuleInterface {
  pickImage(): void;
}

const {ImagePickerModule: ImagePickerNativeModule} = NativeModules;

export const ImagePickerModule =
  ImagePickerNativeModule as ImagePickerModuleInterface;
