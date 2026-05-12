export interface ExamplePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  set(options: { key: string; value: string }): Promise<void>;
  get(options: { key: string }): Promise<{ value: string | null }>;
  remove(options: { key: string }): Promise<void>;
}
